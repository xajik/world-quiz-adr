package com.cvs.worldquiz.quiz;

import android.content.Context;
import android.support.annotation.StringRes;

import com.cvs.worldquiz.db.model.Country;

import java.util.Locale;

import com.cvs.worldquiz.R;

/**
 * @author IgorSteblii on 13.11.16.
 */

public enum QuizQuestionType {

    CAPITAL_OF(1, R.string.what_is_the_capital_of) {
        @Override
        String getQuestionColumn(Country country) {
            return country.getLabel();
        }

        @Override
        Object getAnswerColumn(Country country) {
            return country.getCapital();
        }
    },
    POPULATION_OF(2, R.string.what_is_the_population_of) {
        @Override
        Object getQuestionColumn(Country country) {
            return country.getLabel();
        }

        @Override
        Object getAnswerColumn(Country country) {
            return country.getPopulation();
        }
    },
    CONTINENT_OF(3, R.string.in_what_continent_situated) {
        @Override
        Object getQuestionColumn(Country country) {
            return country.getLabel();
        }

        @Override
        Object getAnswerColumn(Country country) {
            return country.getContinent();
        }
    },
    CITY_IS_CAPITAL(4, R.string.is_the_capital_of) {
        @Override
        Object getQuestionColumn(Country country) {
            return country.getCapital();
        }

        @Override
        Object getAnswerColumn(Country country) {
            return country.getLabel();
        }
    },
    AREA_OF(5, R.string.area_of_is) {
        @Override
        Object getQuestionColumn(Country country) {
            return country.getLabel();
        }

        @Override
        Object getAnswerColumn(Country country) {
            return country.getArea();
        }
    };

    private final int mId;
    @StringRes
    private final int mMessageResourceId;

    QuizQuestionType(int id, int messageResourceId) {
        this.mId = id;
        this.mMessageResourceId = messageResourceId;
    }

    public static QuizQuestionType getById(int id) {
        QuizQuestionType[] values = values();
        for (QuizQuestionType v : values) {
            if (v.getId() == id) {
                return v;
            }
        }
        throw new IllegalArgumentException("Id should exist in list. Id = " + id);
    }

    public String getQuestionMessage(Context context, Country country) {
        String string = context.getString(mMessageResourceId);
        return String.format(Locale.getDefault(), string, getQuestionColumn(country));
    }

    public int getId() {
        return mId;
    }

    abstract Object getQuestionColumn(Country country);

    abstract Object getAnswerColumn(Country country);

}
