package com.andy.test01.service;

import com.andy.test01.domain.Ebook;
import com.andy.test01.domain.EbookExample;
import com.andy.test01.mapper.EbookMapper;
import com.andy.test01.req.EbookReq;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

//import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {

    @Resource
    private EbookMapper ebookMapper;

    public List<Ebook> list(EbookReq req) {
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        criteria.andNameLike("%" + req.getName() + "%");
        return ebookMapper.selectByExample(ebookExample);
    }
}
