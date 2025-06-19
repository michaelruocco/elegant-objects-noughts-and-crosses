package uk.co.mruoc;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Players {

    private final PlayerToken player1;
    private final PlayerToken player2;

    public Players() {
        this(new TokenX(), new TokenO());
    }

    public PlayerToken next() {
        return player1;
    }

    public Players switchNext() {
        return new Players(player2, player1);
    }
}
