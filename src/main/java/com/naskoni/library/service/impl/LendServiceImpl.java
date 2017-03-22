package com.naskoni.library.service.impl;

import static com.naskoni.library.constant.CommonConstants.ASTERISK;
import static com.naskoni.library.constant.CommonConstants.LENDING_DATE;
import static com.naskoni.library.constant.CommonConstants.RETURN_DATE;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naskoni.library.dao.LendDao;
import com.naskoni.library.entity.Lend;
import com.naskoni.library.service.LendService;
import com.naskoni.library.util.ParseUtils;

@Service
public final class LendServiceImpl implements LendService {

  @Autowired
  private LendDao lendDao;

  @Override
  public Lend findLend(long id) {
    return lendDao.findLend(id);
  }

  @Override
  public Set<Lend> findLends() {
    return lendDao.findLends();
  }

  @Override
  public void addLend(Lend lend) {
    lendDao.addLend(lend);
  }

  @Override
  public void updateLend(Lend lend) {
    lendDao.update(lend);
  }

  @Override
  public Set<Lend> findLends(String searchedWord, String searchParam) {
    if (searchedWord.isEmpty() || ASTERISK.equals(searchedWord)) {
      return lendDao.findLends();
    }

    if ((LENDING_DATE.equals(searchParam) || RETURN_DATE.equals(searchParam))
        && ParseUtils.tryParseDate(searchedWord)) {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      try {
        java.util.Date parsed = sdf.parse(searchedWord);
        java.sql.Date searchedDate = new java.sql.Date(parsed.getTime());

        return lendDao.findLends(searchedDate, searchParam);
      } catch (ParseException e) {
        // already caught in ParseUtils
      }
    }

    return lendDao.findLends(searchedWord, searchParam);
  }
}
