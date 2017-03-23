package com.hncu.utils;

import com.google.common.collect.Lists;
import com.hncu.entity.TeacherAndStudent;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 评阅随机分组工具类
 */
public class RandomGroupUtil {

    private static Random random = new Random(new Date().getTime());


    public static void main(String[] args) {
        List<TeacherAndStudent> teacherAndStudentList = Lists.newArrayList();
        for (int i = 0; i < 3; i++) {
            TeacherAndStudent teacherAndStudent = new TeacherAndStudent();
            String x = String.valueOf(i);
            teacherAndStudent.setTeacherId(x);
            teacherAndStudent.setReviewTeacherId(x);
            teacherAndStudentList.add(teacherAndStudent);
            System.out.println(x);
        }
        for (int i = 0; i < teacherAndStudentList.size(); i++) {
            System.out.println(teacherAndStudentList.get(i).getTeacherId() +"========"+teacherAndStudentList.get(i).getReviewTeacherId());
        }
        List<TeacherAndStudent> teacherAndStudentList1 = randomGroup(teacherAndStudentList);
        for (int i = 0; i < teacherAndStudentList1.size(); i++) {
            System.out.println(teacherAndStudentList1.get(i).getTeacherId() +"========"+teacherAndStudentList1.get(i).getReviewTeacherId());
        }
    }



    public static List<TeacherAndStudent> randomGroup(List<TeacherAndStudent> teacherAndStudentList){

        int teacherNum = teacherAndStudentList.size();
        for (int i = 0; i < teacherAndStudentList.size(); i++) {
            teacherAndStudentList.get(i).setReviewTeacherId(teacherAndStudentList.get(i).getTeacherId());
        }
        //判断是奇数还是偶数
        if (teacherNum % 2 == 1) {
            Integer x = random.nextInt(teacherNum);//产生一个小于teacherNum的随机数
            TeacherAndStudent teacherAndStudent = teacherAndStudentList.get(x);
            teacherAndStudentList.remove(teacherAndStudent);
            x = random.nextInt(teacherNum-1);//产生一个小于teacherNum-1的随机数,因为刚刚取出来了一个老师
            TeacherAndStudent teacherAndStudent1 = teacherAndStudentList.get(x);
            String reviewTeacherId = teacherAndStudent1.getReviewTeacherId();
            teacherAndStudentList.get(x).setReviewTeacherId(teacherAndStudent.getReviewTeacherId());
            teacherAndStudent.setReviewTeacherId(reviewTeacherId);
            Collections.shuffle(teacherAndStudentList);//打乱list！
            //现在的老师一定是一个偶数
            for (int i = 0; i < teacherAndStudentList.size() / 2; i++) {
                //前后交换试卷
                String a = teacherAndStudentList.get(i).getReviewTeacherId();
                teacherAndStudentList.get(i).setReviewTeacherId(teacherAndStudentList.get(teacherAndStudentList.size() - 1 - i).getReviewTeacherId());
                teacherAndStudentList.get(teacherAndStudentList.size() - i - 1).setReviewTeacherId(a);
            }
            teacherAndStudentList.add(teacherAndStudent);
        } else {
            Collections.shuffle(teacherAndStudentList);//打乱list！
            for (int i = 0; i < teacherAndStudentList.size() / 2; i++) {
                String a = teacherAndStudentList.get(i).getReviewTeacherId();
                teacherAndStudentList.get(i).setReviewTeacherId(teacherAndStudentList.get(teacherAndStudentList.size() - 1 - i).getReviewTeacherId());
                teacherAndStudentList.get(teacherAndStudentList.size() - i - 1).setReviewTeacherId(a);
            }
        }
        return teacherAndStudentList;
    }
}
