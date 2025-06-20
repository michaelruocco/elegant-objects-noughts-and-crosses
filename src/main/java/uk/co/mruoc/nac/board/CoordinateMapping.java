package uk.co.mruoc.nac.board;

import uk.co.mruoc.nac.Coordinates;

public interface CoordinateMapping {

    Coordinates map(int x, int y);
}
