package org.qboot.modules.system.core.application;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.qboot.common.constant.CommonConstant;
import org.qboot.modules.system.core.domain.SysDict;
import org.qboot.modules.system.core.domain.SysDictItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Date;

@ApplicationScoped
@Transactional
public class SysDictServiceImpl implements SysDictService{
    private static final Logger LOG = LoggerFactory.getLogger(SysDictServiceImpl.class);

    @Override
    public SysDict save(SysDict dict) {
        dict.setCreateTime(new Date());
        dict.setDelFlag(CommonConstant.DEL_FLAG_0);

        dict.persist();

        return dict;
    }

    @Override
    public void update(SysDict dict) {
        SysDict sysdict = SysDict.findById(dict.getId());
        if(sysdict==null){
            throw new RuntimeException("SysDict id="+dict.getId()+"not found");
        }

        sysdict.setDictName(dict.getDictName());
        sysdict.setDescription(dict.getDescription());

        sysdict.persist();
    }

    @Override
    public boolean delete(String id) {
        return SysDict.deleteById(id);
    }

    @Override
    public long delete(Collection<String> ids) {
        return SysDict.delete("id in (?1)", ids);
    }

    @Override
    public SysDict addItem(String dictId,SysDictItem dictItem) {
        SysDict dict = SysDict.findById(dictId); //TODO 没有的情况
        dict.addItem(dictItem);
        dict.persist();
        return dict;
    }

    @Override
    public SysDict deleteItem(String dictId, String itemId) {
        SysDict dict = SysDict.findById(dictId); //TODO 没有的情况
        dict.removeItem(itemId);
        dict.persist();
        return dict;
    }

    @Override
    public SysDict updateItem(String dictId, String itemId, SysDictItem dictItem) {
        SysDict dict = SysDict.findById(dictId);
        dict.updateDictItem(itemId, dictItem);
        dict.persist();
        return dict;
    }
}
