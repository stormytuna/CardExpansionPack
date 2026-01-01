package cardexpansionpack.cards.purple;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class OnSolidGround extends BaseCard {
    public static final String ID = makeID("OnSolidGround");

    private static final CardStats INFO = new CardStats(
        CardColor.COLORLESS,
        CardType.SKILL,
        CardRarity.SPECIAL,
        CardTarget.SELF,
        0
    );

    private static final int BLOCK = 18;
    private static final int BLOCK_UPGRADE = 8;

    public OnSolidGround() {
        super(ID, INFO);
        setBlock(BLOCK, BLOCK_UPGRADE);
        setExhaust(true);
        selfRetain = true;
    }

    @Override
    public AbstractCard makeCopy() {
        return new OnSolidGround();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
    }
}

