package uk.co.mruoc.nac.turn;

import uk.co.mruoc.nac.token.PlayerToken;

public interface Turns {

    Turn next(PlayerToken token);
}
