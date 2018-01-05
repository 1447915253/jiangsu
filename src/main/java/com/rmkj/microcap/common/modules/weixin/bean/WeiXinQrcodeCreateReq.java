package com.rmkj.microcap.common.modules.weixin.bean;

/**
 * Created by 123 on 2016/11/22.
 */
public class WeiXinQrcodeCreateReq {
    /**
     * action_name : QR_LIMIT_STR_SCENE
     * action_info : {"scene":{"scene_str":"123"}}
     */

    private String action_name;
    /**
     * scene : {"scene_str":"123"}
     */

    private ActionInfoBean action_info;

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
         * scene_str : 123
         */

        private SceneBean scene;

        public SceneBean getScene() {
            return scene;
        }

        public void setScene(SceneBean scene) {
            this.scene = scene;
        }

        public static class SceneBean {
            private String scene_str;

            public String getScene_str() {
                return scene_str;
            }

            public void setScene_str(String scene_str) {
                this.scene_str = scene_str;
            }
        }
    }
}
