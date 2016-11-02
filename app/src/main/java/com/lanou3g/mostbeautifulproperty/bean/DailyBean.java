package com.lanou3g.mostbeautifulproperty.bean;

import java.util.List;

/**
 * Created by dllo on 16/10/26.
 */

public class DailyBean {
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
            private DesignerBean designer;
            private String name;
            private int price;
            private String brief;
            private int like_user_num;
            private int mark_user_num;
            private long publish_at;
            private int id;
            private List<String> cover_images;
            private List<String> images;
            private int num;

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

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

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
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
