package com.wangbowang.spring.boot.web.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private String project;
    private String name;
    private String role;
    private String descriptions;
    private String clusterName;
    private AppProperties.Datasource datasource;
    private boolean verboseMessage = true;

    public AppProperties() {
    }

    public void setProject(String project) {
        this.project = project;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public void setDatasource(AppProperties.Datasource datasource) {
        this.datasource = datasource;
    }

    public void setVerboseMessage(boolean verboseMessage) {
        this.verboseMessage = verboseMessage;
    }

    public String getProject() {
        return this.project;
    }

    public String getName() {
        return this.name;
    }

    public String getRole() {
        return this.role;
    }

    public String getDescriptions() {
        return this.descriptions;
    }

    public String getClusterName() {
        return this.clusterName;
    }

    public AppProperties.Datasource getDatasource() {
        return this.datasource;
    }

    public boolean isVerboseMessage() {
        return this.verboseMessage;
    }

    private class Datasource {
        private String address;
        private String host;
        private String port;
        private String roHost;
        private String username;
        private String password;
        private String name;
        private AppProperties.Datasource.Druid druid;

        private Datasource() {
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public void setPort(String port) {
            this.port = port;
        }

        public void setRoHost(String roHost) {
            this.roHost = roHost;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setDruid(AppProperties.Datasource.Druid druid) {
            this.druid = druid;
        }

        public String getAddress() {
            return this.address;
        }

        public String getHost() {
            return this.host;
        }

        public String getPort() {
            return this.port;
        }

        public String getRoHost() {
            return this.roHost;
        }

        public String getUsername() {
            return this.username;
        }

        public String getPassword() {
            return this.password;
        }

        public String getName() {
            return this.name;
        }

        public AppProperties.Datasource.Druid getDruid() {
            return this.druid;
        }

        private class Druid {
            private String privateKey;
            private String publicKey;

            private Druid() {
            }

            public void setPrivateKey(String privateKey) {
                this.privateKey = privateKey;
            }

            public void setPublicKey(String publicKey) {
                this.publicKey = publicKey;
            }

            public String getPrivateKey() {
                return this.privateKey;
            }

            public String getPublicKey() {
                return this.publicKey;
            }
        }
    }
}
