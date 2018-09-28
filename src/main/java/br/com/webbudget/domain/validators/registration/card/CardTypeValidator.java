/*
 * Copyright (C) 2018 Arthur Gregorio, AG.Software
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.com.webbudget.domain.validators.registration.card;

import br.com.webbudget.domain.entities.registration.Card;
import br.com.webbudget.domain.entities.registration.CardType;
import br.com.webbudget.domain.exceptions.BusinessLogicException;

import javax.enterprise.context.Dependent;

/**
 * The {@link CardType} validator
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 3.0.0, 27/09/2018
 */
@Dependent
public class CardTypeValidator implements CardSavingValidator, CardUpdatingValidator {

    /**
     * {@inheritDoc }
     *
     * @param value the value to work with
     */
    @Override
    public void validate(Card value) {

        final CardType cardType = value.getCardType();

        if (cardType == CardType.DEBIT && value.getWallet() == null) {
            throw BusinessLogicException.create("error.card.no-wallet");
        }
    }
}