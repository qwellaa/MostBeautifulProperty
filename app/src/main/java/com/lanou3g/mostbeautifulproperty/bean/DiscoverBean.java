package com.lanou3g.mostbeautifulproperty.bean;

import java.util.List;

/**
 * Created by dllo on 16/10/27.
 */

public class DiscoverBean {

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
        /**
         * unlike_user_num : 27
         * designer : {"city":"乌拉圭","concept":"寻觅对美感的新定义","name":"Deborah & Dinorah Kaiser","label":"Homini 创始人","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/10/25/742b46f6-7e81-42df-9d25-3e9850514046.jpg","id":146}
         * name : Homini | T 型棱边戒指
         * cover_images : ["http://dstatic.zuimeia.com/common/image/2016/10/26/1599bf35-ffaa-4afa-9c8c-2b4d30cc9696_948x948.jpeg","http://dstatic.zuimeia.com/common/image/2016/10/26/8f028ad6-a798-4207-a8d7-ce298dcfb8af_984x984.jpeg","http://dstatic.zuimeia.com/common/image/2016/10/26/ca9bc22b-1e3a-457c-82a7-cae6fbf8f97d_984x984.jpeg","http://dstatic.zuimeia.com/common/image/2016/10/26/6ef2cb0c-ee88-4f8e-b1ed-9f609caacad1_984x984.jpeg"]
         * price : 540.0
         * brief : Homini | T 型棱边戒指
         * like_user_num : 50
         * mark_user_num : 2
         * images : ["http://dstatic.zuimeia.com/common/image/2016/10/26/5fed9094-e3ef-477f-9bf1-38451f0747c7_984x984.jpeg"]
         * publish_at : 1477497600000
         * id : 1431
         */

        private List<ProductsBean> products;

        public int getHas_next() {
            return has_next;
        }

        public void setHas_next(int has_next) {
            this.has_next = has_next;
        }

        public List<ProductsBean> getProducts() {
            return products;
        }

        public void setProducts(List<ProductsBean> products) {
            this.products = products;
        }

        public static class ProductsBean {
            private int unlike_user_num;
            /**
             * city : 乌拉圭
             * concept : 寻觅对美感的新定义
             * name : Deborah & Dinorah Kaiser
             * label : Homini 创始人
             * avatar_url : http://dstatic.zuimeia.com/designer/avatar/2016/10/25/742b46f6-7e81-42df-9d25-3e9850514046.jpg
             * id : 146
             */

            private DesignerBean designer;
            private String name;
            private double price;
            private String brief;
            private int like_user_num;
            private int mark_user_num;
            private long publish_at;
            private int id;
            private List<String> cover_images;
            private List<String> images;

            public int getUnlike_user_num() {
                return unlike_user_num;
            }

            public void setUnlike_user_num(int unlike_user_num) {
                this.unlike_user_num = unlike_user_num;
            }

            public DesignerBean getDesigner() {
                return designer;
            }

            public void setDesigner(DesignerBean designer) {
                this.designer = designer;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public String getBrief() {
                return brief;
            }

            public void setBrief(String brief) {
                this.brief = brief;
            }

            public int getLike_user_num() {
                return like_user_num;
            }

            public void setLike_user_num(int like_user_num) {
                this.like_user_num = like_user_num;
            }

            public int getMark_user_num() {
                return mark_user_num;
            }

            public void setMark_user_num(int mark_user_num) {
                this.mark_user_num = mark_user_num;
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

            public List<String> getCover_images() {
                return cover_images;
            }

            public void setCover_images(List<String> cover_images) {
                this.cover_images = cover_images;
            }

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }

            public static class DesignerBean {
                private String city;
                private String concept;
                private String name;
                private String label;
                private String avatar_url;
                private int id;

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

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }
            }
        }
    }
}
