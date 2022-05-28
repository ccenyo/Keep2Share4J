package org.github.views;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Keep2ShareFileListView extends DefaultView{

    private List<FileView> files;

    public List<FileView> getFiles() {
        return files;
    }

    public static class FileView {
        private String id;
        private String name;
        private boolean is_available;
        private boolean is_folder;
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss")
        private Date date_created;
        private long size;
        private String md5;
        private ExtendedInfo extended_info;


        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public boolean isIs_available() {
            return is_available;
        }

        public boolean isIs_folder() {
            return is_folder;
        }

        public Date getDate_created() {
            return date_created;
        }

        public long getSize() {
            return size;
        }

        public String getMd5() {
            return md5;
        }

        public ExtendedInfo getExtended_info() {
            return extended_info;
        }

        public static class ExtendedInfo {
            private  String storage_object;
            private long size;

            @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
            private Date date_download_last;
            private long downloads;
            private String access;
            private String content_type;
            private List<Abuse> abuses;

            public String getStorage_object() {
                return storage_object;
            }

            public long getSize() {
                return size;
            }

            public Date getDate_download_last() {
                return date_download_last;
            }

            public long getDownloads() {
                return downloads;
            }

            public String getAccess() {
                return access;
            }

            public String getContent_type() {
                return content_type;
            }

            public List<Abuse> getAbuses() {
                return abuses;
            }

            public static class Abuse {
                private String type;
                private List<String> projects;
                private LocalDateTime blocked_to;

                public String getType() {
                    return type;
                }

                public List<String> getProjects() {
                    return projects;
                }

                public LocalDateTime getBlocked_to() {
                    return blocked_to;
                }
            }
        }
    }
}
