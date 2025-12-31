package cardexpansionpack.cards.purple;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class Patience extends BaseCard {
    public static final String ID = makeID("Patience");

    private static final CardStats INFO = new CardStats(
        CardColor.PURPLE,
        CardType.SKILL,
        CardRarity.UNCOMMON,
        CardTarget.SELF,
        0
    );

    private static final int BASE_DRAW = 1;
    private static final int BASE_DRAW_UPGRADE = 1;
    private static final int DRAW_PER_RETAIN = 1;

    public Patience() {
        super(ID, INFO);
        setMagic(BASE_DRAW, BASE_DRAW_UPGRADE);
        setExhaust(true);
        selfRetain = true;
    }

    @Override
    public AbstractCard makeCopy() {
        return new Patience();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(magicNumber));
    }

    @Override
    public void onRetained() {
        upgradeMagicNumber(DRAW_PER_RETAIN);
        if (!upgraded && magicNumber > 1) {
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}

