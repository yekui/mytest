/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.test.statistic.metric;


import com.test.statistic.base.WindowWrap;

import java.util.List;

/**
 * create by dengkui 2018.10.10
 */
public class ArrayMetric implements Metric {

    private final MetricsLeapArray data;

    public ArrayMetric(int windowLengthInMs, int intervalInSec) {
        this.data = new MetricsLeapArray(windowLengthInMs, intervalInSec);
    }

    public ArrayMetric(MetricsLeapArray array) {
        this.data = array;
    }

    @Override
    public long count() {
        data.currentWindow();
        long success = 0;

        List<QpsMetricBucket> list = data.values();
        for (QpsMetricBucket window : list) {
            success += window.count();
        }
        return success;
    }

    @Override
    public QpsMetricBucket[] windows() {
        data.currentWindow();
        return data.values().toArray(new QpsMetricBucket[data.values().size()]);
    }

    @Override
    public void addCount() {
        WindowWrap<QpsMetricBucket> wrap = data.currentWindow();
        wrap.value().addCount();
    }
}
