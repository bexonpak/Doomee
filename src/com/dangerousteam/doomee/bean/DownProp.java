/*
 * Copyright (C) 2020 Bexon Pak.
 */
package com.dangerousteam.doomee.bean;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;

public class DownProp implements Serializable {
    @Serial
    private static final long serialVersionUID = -4755884196718985637L;

    private String output;
    private String[] url;

    public DownProp() {
    }

    public String getOutput() {
        return output;
    }

    public String[] getUrl() {
        return url;
    }

    public static class Builder {
        private DownProp downProp;

        public Builder() {
            downProp = new DownProp();
        }

        public Builder output(String output) {
            downProp.output = output;
            return this;
        }

        public Builder url(String... url) {
            downProp.url = url;
            return this;
        }

        public DownProp build() {
            return downProp;
        }
    }

    @Override
    public String toString() {
        return "{\"DownProp\":{"
                + "\"output\":\"" + output + "\""
                + ", \"url\":" + Arrays.toString(url)
                + "}}";
    }
}