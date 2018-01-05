package com.rmkj.microcap.common.modules.weixin.bean;

import java.math.BigInteger;

/**
 * Created by 123 on 2017/3/13.
 */
public class WeiXinQrcodeTempCreateReq {
    /**
     * expire_seconds : 604800
     * action_name : QR_SCENE
     * action_info : {"scene":{"scene_id":123}}
     */

    private int expire_seconds;
    private String action_name;
    /**
     * scene : {"scene_id":123}
     */

    private ActionInfoBean action_info;

    public int getExpire_seconds() {
        return expire_seconds;
    }

    public void setExpire_seconds(int expire_seconds) {
        this.expire_seconds = expire_seconds;
    }

    public String getAction_name() {
        return action_name;
    }

    public void setAction_name(String action_name) {
        this.action_name = action_name;
    }

    public ActionInfoBean getAction_info() {
        return action_info;
    }

    public void setAction_info(ActionInfoBean action_info) {
        this.action_info = action_info;
    }

    public static class ActionInfoBean {
        /**
         * scene_id : 123
         */

        private SceneBean scene;

        public SceneBean getScene() {
            return scene;
        }

        public void setScene(SceneBean scene) {
            this.scene = scene;
        }

        public static class SceneBean {
            private BigInteger scene_id;

            public BigInteger getScene_id() {
                return scene_id;
            }

            public void setScene_id(BigInteger scene_id) {
                this.scene_id = scene_id;
            }
        }
    }
}
