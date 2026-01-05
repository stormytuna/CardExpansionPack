package cardexpansionpack.cards.green;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class DuckAndWeave extends BaseCard {
    public static final String ID = makeID("DuckAndWeave");

    private static final CardStats INFO = new CardStats(
        CardColor.GREEN,
        CardType.SKILL,
        CardRarity.COMMON,
        CardTarget.SELF,
        1
    );

    private static final int BLOCK = 3;
    private static final int BLOCK_TIMES = 2;
    private static final int BLOCK_TIMES_UPGRADE = 1;

    public DuckAndWeave() {
        super(ID, INFO);
        setBlock(BLOCK);
        setMagic(BLOCK_TIMES, BLOCK_TIMES_UPGRADE);
    }

    @Override
    public AbstractCard makeCopy() {
        return new DuckAndWeave();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            addToBot(new GainBlockAction(p, block));
        }
    }
}
