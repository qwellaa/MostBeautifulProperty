package com.lanou3g.mostbeautifulproperty.bean;

import java.util.List;

/**
 *
 */

public class MagazineBean {

    private DataBean data;
    private int result;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public static class DataBean {
        private int has_next;
        private List<ArticlesBean> articles;

        public int getHas_next() {
            return has_next;
        }

        public void setHas_next(int has_next) {
            this.has_next = has_next;
        }

        public List<ArticlesBean> getArticles() {
            return articles;
        }

        public void setArticles(List<ArticlesBean> articles) {
            this.articles = articles;
        }

        public static class ArticlesBean {
            /**
             * username : 夏漱香菜
             * avatar_url : http://dstatic.zuimeia.com/user/avatar/2016/08/27/5a069cd6278626c9a638bf46a8010837_450x450.jpg
             * id : 41625
             * sign : 迷失风向却沉浸在时尚海洋里的游鱼
             */

            private AuthorBean author;
            private String title;
            private String sub_title;
            private int favor_user_num;
            private String web_url;
            private String image_url;
            private long publish_at;
            private int id;

            public AuthorBean getAuthor() {
                return author;
            }

            public void setAuthor(AuthorBean author) {
                this.author = author;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSub_title() {
                return sub_title;
            }

            public void setSub_title(String sub_title) {
                this.sub_title = sub_title;
            }

            public int getFavor_user_num() {
                return favor_user_num;
            }

            public void setFavor_user_num(int favor_user_num) {
                this.favor_user_num = favor_user_num;
            }

            public String getWeb_url() {
                return web_url;
            }

            public void setWeb_url(String web_url) {
                this.web_url = web_url;
            }

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public long getPublish_at() {
                return publish_at;
            }

            public void setPublish_at(long publish_at) {
                this.publish_at = publish_at;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public static class AuthorBean {
                private String username;
                private String avatar_url;
                private int id;
                private String sign;

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }

                public String getAvatar_url() {
                    return avatar_url;
                }

                public void setAvatar_url(String avatar_url) {
                    this.avatar_url = avatar_url;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getSign() {
                    return sign;
                }

                public void setSign(String sign) {
                    this.sign = sign;
                }
            }
        }
    }
}
