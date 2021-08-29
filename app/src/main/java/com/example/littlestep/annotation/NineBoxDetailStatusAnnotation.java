package com.example.littlestep.annotation;

import androidx.annotation.IntDef;

import com.example.littlestep.common.NineBoxConstants;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 九宫格明细的状态
 * <p>
 * create on 2021/8/29 20:44
 * Email:923998007@qq.com
 *
 * @author lin
 */
@IntDef(value = {
        NineBoxConstants.DetailStatus.BLANK,
        NineBoxConstants.DetailStatus.DOING,
        NineBoxConstants.DetailStatus.FINISH,
})
@Retention(RetentionPolicy.SOURCE)
public @interface NineBoxDetailStatusAnnotation {
}
