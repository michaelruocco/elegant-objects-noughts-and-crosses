package uk.co.mruoc;

public class RandomTurns implements Turns {

    @Override
    public Turn next(PlayerToken token) {
        return new RandomTurn(token);
    }
}
