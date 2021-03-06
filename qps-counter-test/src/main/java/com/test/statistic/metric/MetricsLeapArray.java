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

import com.test.statistic.base.LeapArray;
import com.test.statistic.base.WindowWrap;

/**
 * create by dengkui 2018.10.10
 */
public class MetricsLeapArray extends LeapArray<QpsMetricBucket> {

    public MetricsLeapArray(int windowLengthInMs, int intervalInSec) {
        super(windowLengthInMs, intervalInSec);
    }

    @Override
    public QpsMetricBucket newEmptyBucket() {
        return new QpsMetricBucket();
    }

    @Override
    protected WindowWrap<QpsMetricBucket> resetWindowTo(WindowWrap<QpsMetricBucket> w, long startTime) {
        w.resetTo(startTime);
        w.value().reset();
        return w;
    }
}
