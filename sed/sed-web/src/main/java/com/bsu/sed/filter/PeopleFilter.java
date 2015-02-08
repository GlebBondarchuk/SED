package com.bsu.sed.filter;

import com.bsu.sed.model.persistent.People;
import net.sf.junidecode.Junidecode;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author gbondarchuk
 */
@Component
public class PeopleFilter {
    public People transliterate(People people) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        if(lang.equals("en")) {
            String username = people.getUser().getName();
            username = Junidecode.unidecode(username);
            people.getUser().setName(username);
        }
        return people;
    }
}
