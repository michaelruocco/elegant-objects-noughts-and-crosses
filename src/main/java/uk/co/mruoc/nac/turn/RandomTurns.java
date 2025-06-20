package uk.co.mruoc.nac.turn;

import uk.co.mruoc.nac.token.PlayerToken;

public class RandomTurns implements Turns {

    @Override
    public Turn next(PlayerToken token) {
        return new RandomTurn(token);
    }
}
