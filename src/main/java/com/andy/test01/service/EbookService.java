package com.andy.test01.service;

import com.andy.test01.domain.Ebook;
import com.andy.test01.domain.EbookExample;
import com.andy.test01.mapper.EbookMapper;
import com.andy.test01.req.EbookReq;
import com.andy.test01.resp.EbookResp;
import com.andy.test01.resp.PageResq;
import com.andy.test01.util.CopyUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class EbookService {

    private static final Logger LOG = LoggerFactory.getLogger(EbookService.class);

    @Resource
    private EbookMapper ebookMapper;

    public PageResq<EbookResp> list(EbookReq req) {
        PageHelper.startPage(req.getPage(), req.getSize());
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        if (!ObjectUtils.isEmpty(req.getName())) {
            criteria.andNameLike("%" + req.getName() + "%");
        }
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);

        PageInfo<Ebook> pageInfo = new PageInfo<>(ebookList);
        LOG.info("总行数: {}", pageInfo.getTotal());
        LOG.info("总页数: {}", pageInfo.getPages());

//        List<EbookResp> respList = new ArrayList<>();
//        for (Ebook ebook : ebookList) {
//            EbookResp ebookResp = new EbookResp();
//            BeanUtils.copyProperties(ebook, ebookResp);
//            respList.add(ebookResp);
//        }


        // 列表复制
        List<EbookResp> list = CopyUtil.copyList(ebookList, EbookResp.class);

        PageResq<EbookResp> pageResq = new PageResq<>();
        pageResq.setTotal(pageInfo.getTotal());
        pageResq.setList(list);

        return pageResq;
    }
}
