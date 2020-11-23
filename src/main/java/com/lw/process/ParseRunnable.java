package com.lw.process;

import com.lw.entity.BaseRequest;
import com.lw.entity.BaseResponse;

public interface ParseRunnable {
    BaseResponse run(BaseRequest baseRequest);
}
