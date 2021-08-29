package com.example.littlestep.common;

/**
 * create on 2021/8/29 20:47
 * Email:923998007@qq.com
 *
 * @author lin
 */
public class NineBoxConstants {

    /**
     * 九宫格明细状态
     */
    public interface DetailStatus {
        /**
         * 空白状态
         */
        int BLANK = 0;
        /**
         * 进行态
         */
        int DOING = 1;
        /**
         * 已完成
         */
        int FINISH = 2;
    }

    public interface NineBoxItemKey {
        /**
         * 事业
         */
        String CAUSE = "cause";
        /**
         * 健康
         */
        String HEALTHY = "healthy";
        /**
         * 理财
         */
        String MONEY = "money";
        /**
         * 人脉
         */
        String CONTACTS = "contacts";

        /**
         * 家庭
         */
        String FAMILY = "family";
        /**
         * 学习
         */
        String STUDY = "study";

        /**
         * 休闲
         */
        String LEISURE = "leisure";

        /**
         * 心灵
         */
        String HEART = "heart";
    }

}
