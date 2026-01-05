package cardexpansionpack.cards.green;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class Smokescreen extends BaseCard {
    public static final String ID = makeID("Smokescreen");

    private static final CardStats INFO = new CardStats(
        CardColor.GREEN,
        CardType.SKILL,
        CardRarity.COMMON,
        CardTarget.SELF,
        1
    );

    private static final int BLOCK = 3;
    private static final int BLOCK_UPGRADE = 1;

    public Smokescreen() {
        super(ID, INFO);
        setBlock(BLOCK, BLOCK_UPGRADE);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Smokescreen();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        String[] uiStrings = CardCrawlGame.languagePack.getUIString(ID).TEXT;
        addToBot(new SelectCardsInHandAction(p.hand.size(), "Discard.", true, true, filter -> true, cards -> {
            int numCards = cards.size();
            for (int i = 0; i < numCards; i++) {
                addToBot(new DiscardSpecificCardAction(cards.get(i)));
                addToBot(new GainBlockAction(p, block));
            }
        }));
    }
}
