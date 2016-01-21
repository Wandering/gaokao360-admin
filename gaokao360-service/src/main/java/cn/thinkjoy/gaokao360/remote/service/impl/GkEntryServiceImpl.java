package cn.thinkjoy.gaokao360.remote.service.impl;

import cn.thinkjoy.common.domain.SearchFilter;
import cn.thinkjoy.gaokao360.domain.Vocabulary;
import cn.thinkjoy.gaokao360.remote.service.impl.base.BaseCommonService;
import cn.thinkjoy.gaokao360.service.common.IVocabularyService;
import cn.thinkjoy.zgk.common.QueryUtil;
import cn.thinkjoy.zgk.domain.BizData4Page;
import cn.thinkjoy.zgk.domain.GkEntry;
import cn.thinkjoy.zgk.remote.IGkEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2016/1/21.
 */
@Service("GkEntryServiceImpl")
public class GkEntryServiceImpl extends BaseCommonService implements IGkEntryService {
    @Autowired
    IVocabularyService vocabularyService;

    @Override
    public BizData4Page getGkEntryList(Map<String, Object> conditions, Integer page, Integer rows) {
        this.setIsIgnore(false);
        return doPage(conditions,vocabularyService.getDao(),page,rows);
    }

    @Override
    public GkEntry getGkEntryInfo(Object id) {
        this.setIsIgnore(true);
        return vocabulary2GkEntry((Vocabulary) vocabularyService.fetch(id));
    }

    @Override
    protected Object enhanceStateTransition(List conditions) {
        return vocabulary2GkEntry(conditions);
    }

    @Override
    protected void enhanceSearchFilter(Map<String, Object> conditions) {
        QueryUtil.setMapOp(conditions,"categoryId","=","2");
    }


    private GkEntry vocabulary2GkEntry(Vocabulary vocabulary){
        if(vocabulary==null)return null;
        GkEntry gkEntry=new GkEntry();
        gkEntry.setId(vocabulary.getId());
        gkEntry.setHits(vocabulary.getHits());
        gkEntry.setSummary(vocabulary.getSummary());
        gkEntry.setLastModDate(vocabulary.getLastModDate());
        gkEntry.setTitle(vocabulary.getTitle());
        gkEntry.setSortId(vocabulary.getSortId());
        if(isIgnore()){
            gkEntry.setContent(vocabulary.getContent());
        }
        return gkEntry;
    }

    private List<GkEntry> vocabulary2GkEntry(List<Vocabulary> vocabularys){
        if(vocabularys==null)return null;
        List<GkEntry> gkEntrys = new ArrayList<>();
        for(Vocabulary vocabulary:vocabularys){
            gkEntrys.add(vocabulary2GkEntry(vocabulary));
        }
        return gkEntrys;
    }
}
