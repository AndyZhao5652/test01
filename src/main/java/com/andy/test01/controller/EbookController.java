package com.andy.test01.controller;

import com.andy.test01.req.EbookReq;
import com.andy.test01.resp.CommonResp;
import com.andy.test01.resp.EbookResp;
import com.andy.test01.resp.PageResq;
import com.andy.test01.service.EbookService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public CommonResp list(EbookReq req) {
        CommonResp<PageResq<EbookResp>> resp = new CommonResp<>();
        PageResq<EbookResp> list = ebookService.list(req);
        resp.setContent(list);
        return resp;
    }

}
