package cardexpansionpack.cards.red;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class RecklessAbandon extends BaseCard {
    public static final String ID = makeID("RecklessAbandon");

    private static final CardStats INFO = new CardStats(
        CardColor.RED,
        CardType.SKILL,
        CardRarity.UNCOMMON,
        CardTarget.SELF,
        0
    );

    private static final int DRAW = 2;
    private static final int DRAW_UPGRADE = 1;
    private static final int DAZED_AMOUNT = 2;

    public RecklessAbandon() {
        super(ID, INFO);
        setMagic(DRAW, DRAW_UPGRADE);
        cardsToPreview = new Dazed();
    }

    @Override
    public AbstractCard makeCopy() {
        return new RecklessAbandon();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(magicNumber));
        addToBot(new MakeTempCardInDrawPileAction(new Dazed(), DAZED_AMOUNT, false, true));
    }
}

