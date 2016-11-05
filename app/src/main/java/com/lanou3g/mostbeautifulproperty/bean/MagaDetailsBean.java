package com.lanou3g.mostbeautifulproperty.bean;

import java.util.List;

/**
 *
 */

public class MagaDetailsBean {
    private Databean data;
    private int result;

    @Override
    public String toString() {
        return "SecondBean{" +
                "data=" + data +
                ", result=" + result +
                '}';
    }

    public Databean getData() {
        return data;
    }

    public void setData(Databean data) {
        this.data = data;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public static class Databean {
        private String title;
        private String sub_title;
        private Authorbean author;
        private String content;
        private String web_url;
        private String image_url;
        private List<DesignersBean> designers;

        @Override
        public String toString() {
            return "Databean{" +
                    "designers=" + designers +
                    ", image_url='" + image_url + '\'' +
                    ", web_url='" + web_url + '\'' +
                    ", content='" + content + '\'' +
                    ", author=" + author +
                    ", sub_title='" + sub_title + '\'' +
                    ", title='" + title + '\'' +
                    '}';
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

        public Authorbean getAuthor() {
            return author;
        }

        public void setAuthor(Authorbean author) {
            this.author = author;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

        public List<DesignersBean> getDesigners() {
            return designers;
        }

        public void setDesigners(List<DesignersBean> designers) {
            this.designers = designers;
        }

        public static class Authorbean {
            private String username;
            private String avatar_url;
            private int id;
            private String sign;

            @Override
            public String toString() {
                return "Authorbean{" +
                        "username='" + username + '\'' +
                        ", avatar_url='" + avatar_url + '\'' +
                        ", id=" + id +
                        ", sign='" + sign + '\'' +
                        '}';
            }

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

        public static class DesignersBean {
            private String city;
            private String concept;
            private String name;
            private String label;
            private String avatar_url;
            private String description;
            private int id;
            private int follow_num;

            public int getFollow_num() {
                return follow_num;
            }

            public void setFollow_num(int follow_num) {
                this.follow_num = follow_num;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getConcept() {
                return concept;
            }

            public void setConcept(String concept) {
                this.concept = concept;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getAvatar_url() {
                return avatar_url;
            }

            public void setAvatar_url(String avatar_url) {
                this.avatar_url = avatar_url;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }
}
