package cardexpansionpack.cards.green;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.Reflex;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import cardexpansionpack.actions.AddFromDiscardWithExclusionsAction;
import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class Trace extends BaseCard {
    public static final String ID = makeID("Trace");

    private static final CardStats INFO = new CardStats(
        CardColor.GREEN,
        CardType.SKILL,
        CardRarity.UNCOMMON,
        CardTarget.NONE,
        -2
    );

    private static final int CARDS_TO_FETCH = 1;
    private static final int CARD_TO_FETCH_UPGRADE = 1;

    private static final String cantUseMessage = CardCrawlGame.languagePack.getCardStrings(Reflex.ID).EXTENDED_DESCRIPTION[0];

    public Trace() {
        super(ID, INFO);
        setMagic(CARDS_TO_FETCH, CARD_TO_FETCH_UPGRADE);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Trace();
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        super.cantUseMessage = cantUseMessage;
        return false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AddFromDiscardWithExclusionsAction(magicNumber, ID));
    }

    @Override
    public void triggerOnManualDiscard() {
        addToBot(new AddFromDiscardWithExclusionsAction(magicNumber, ID));
    }
}
