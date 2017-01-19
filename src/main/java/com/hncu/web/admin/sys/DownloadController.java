package com.hncu.web.admin.sys;

import com.github.pagehelper.PageInfo;
import com.hncu.common.BaseController;
import com.hncu.common.Msg;
import com.hncu.common.PageParam;
import com.hncu.entity.DownLoad;
import com.hncu.service.admin.sys.DownloadService;
import com.hncu.utils.MyFileUtil;
import com.hncu.utils.StringUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

/**
 * 下载
 */
@Controller
@RequestMapping("/admin")
public class DownloadController extends BaseController {

    @Resource
    private DownloadService downloadService;


    @ModelAttribute
    public DownLoad get(@RequestParam(required = false) String id) {
        if (StringUtils.isBlank(id)){
            return new DownLoad();
        } else {
            return downloadService.queryById(id);
        }
    }

    @RequestMapping(value = "/downloadList")
    public String queryDownloadList(DownLoad downLoad, Model model, PageParam pageParam){
        PageInfo<DownLoad> downLoadPageInfo = downloadService.queryListWithPage(downLoad, pageParam);
        model.addAttribute("downLoadPageInfo", downLoadPageInfo);
        return "admin/sys/download";
    }

    @RequestMapping(value = "/downloadData", produces = "application/octet-stream;charset=UTF-8")
    public ResponseEntity<byte[]> download(@RequestParam String id) throws IOException {
        DownLoad downLoad = downloadService.queryById(id);

        //todo 判断空
            File file = new File(downLoad.getUploadPath());
            String dfileName = downLoad.getUploadFileOldName();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", new String(dfileName.getBytes("UTF-8"), "ISO8859-1"));
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/deleteData")
    public String deleteData(@RequestParam String id, RedirectAttributes redirectAttributes){
        DownLoad downLoad = downloadService.queryById(id);
        if (downLoad != null){
            if (MyFileUtil.deleteFile(downLoad.getUploadPath())){
                downloadService.delete(downLoad);
                redirectAttributes.addFlashAttribute("msg", new Msg(Msg.MSG_TYPE_OK, "【"+downLoad.getTitle()+"】删除成功！！"));
            } else {
                downloadService.delete(downLoad);
                redirectAttributes.addFlashAttribute("msg", new Msg(Msg.MSG_TYPE_REMOVE, "【"+downLoad.getTitle()+"】不存在或者已被删除！！"));
            }
        } else {
            redirectAttributes.addFlashAttribute("msg", new Msg(Msg.MSG_TYPE_REMOVE, "编号为【"+id+"】不存在！！"));
        }
        return "redirect:/admin/downloadList";
    }
}
