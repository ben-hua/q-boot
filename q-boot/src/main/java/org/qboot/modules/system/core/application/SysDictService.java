package org.qboot.modules.system.core.application;

import org.qboot.modules.system.core.domain.SysDict;
import org.qboot.modules.system.core.domain.SysDictItem;

import java.util.Collection;

public interface SysDictService {
    SysDict save(SysDict dict);

    void update(SysDict dict);

    boolean delete(String id);

    long delete(Collection<String> ids);

    SysDict addItem(String dictId,SysDictItem dictItem);

    SysDict deleteItem(String dictId, String itemId);

    SysDict updateItem(String dictId, String itemId, SysDictItem dictItem);
}
