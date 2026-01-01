package cardexpansionpack.cards.purple;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Insight;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class GlimpseFuture extends BaseCard {
    public static final String ID = makeID("GlimpseFuture");

    private static final CardStats INFO = new CardStats(
        CardColor.PURPLE,
        CardType.SKILL,
        CardRarity.UNCOMMON,
        CardTarget.SELF,
        1
    );

    private static final int NUM_INSIGHT = 2;

    public GlimpseFuture() {
        super(ID, INFO);
        setEthereal(true, false);
        setMagic(NUM_INSIGHT);
        setExhaust(true);
        cardsToPreview = new Insight();
    }

    @Override
    public AbstractCard makeCopy() {
        return new GlimpseFuture();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MakeTempCardInHandAction(new Insight(), magicNumber));
    }
}

