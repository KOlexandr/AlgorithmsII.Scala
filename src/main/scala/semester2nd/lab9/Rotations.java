package semester2nd.lab9;

public enum Rotations {
    CLOCK0 {
        @Override
        public Block rotate(Block block) {
            return block;
        }

        @Override
        public Block inverse(Block block) {
            return block;
        }
    },
    CLOCK90 {
        @Override
        public Block rotate(Block block) {
            return new Block(rotateMatrixRight(block.data()), block.i(), block.j());
        }

        @Override
        public Block inverse(Block block) {
            return new Block(rotateMatrixLeft(block.data()), block.i(), block.j());
        }
    },
    CLOCK180 {
        @Override
        public Block rotate(Block block) {
            return new Block(rotateMatrixLeft(rotateMatrixLeft(block.data())), block.i(), block.j());
        }

        @Override
        public Block inverse(Block block) {
            return new Block(rotateMatrixRight(rotateMatrixRight(block.data())), block.i(), block.j());
        }
    },
    CLOCK270 {
        @Override
        public Block rotate(Block block) {
            return new Block(rotateMatrixLeft(block.data()), block.i(), block.j());
        }

        @Override
        public Block inverse(Block block) {
            return new Block(rotateMatrixRight(block.data()), block.i(), block.j());
        }
    },
    SYMMETRY0 {
        @Override
        public Block rotate(Block block) {
            return block.fliplr();
        }

        @Override
        public Block inverse(Block block) {
            return block.fliplr();
        }
    },
    SYMMETRY90 {
        @Override
        public Block rotate(Block block) {
            return CLOCK90.rotate(block.fliplr());
        }

        @Override
        public Block inverse(Block block) {
            return CLOCK90.inverse(block.fliplr());
        }
    },
    SYMMETRY180 {
        @Override
        public Block rotate(Block block) {
            return CLOCK180.rotate(block.fliplr());
        }

        @Override
        public Block inverse(Block block) {
            return CLOCK180.inverse(block.fliplr());
        }
    },
    SYMMETRY270 {
        @Override
        public Block rotate(Block block) {
            return CLOCK270.rotate(block.fliplr());
        }

        @Override
        public Block inverse(Block block) {
            return CLOCK270.inverse(block.fliplr());
        }
    };

    public abstract Block rotate(Block block);
    public abstract Block inverse(Block block);

    public double[][] rotateMatrixRight(final double[][] matrix) {
        /* W and H are already swapped */
        final int w = matrix.length;
        final int h = matrix[0].length;
        final double[][] ret = new double[h][w];
        for (int i = 0; i < h; ++i) {
            for (int j = 0; j < w; ++j) {
                ret[i][j] = matrix[w - j - 1][i];
            }
        }
        return ret;
    }


    public double[][] rotateMatrixLeft(final double[][] matrix) {
        /* W and H are already swapped */
        final int w = matrix.length;
        final int h = matrix[0].length;
        final double[][] ret = new double[h][w];
        for (int i = 0; i < h; ++i) {
            for (int j = 0; j < w; ++j) {
                ret[i][j] = matrix[j][h - i - 1];
            }
        }
        return ret;
    }
}
