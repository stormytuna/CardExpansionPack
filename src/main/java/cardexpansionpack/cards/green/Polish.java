package cardexpansionpack.cards.green;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class Polish extends BaseCard {
    public static final String ID = makeID("Polish");

    private static final CardStats INFO = new CardStats(
        CardColor.GREEN,
        CardType.SKILL,
        CardRarity.COMMON,
        CardTarget.SELF,
        0
    );

    private static final int NUM_CARDS = 2;
    private static final int NUM_CARDS_UPGRADE = 1;

    public Polish() {
        super(ID, INFO);
        setMagic(NUM_CARDS, NUM_CARDS_UPGRADE);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Polish();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        String upgradeText = CardCrawlGame.languagePack.getUIString("ArmamentsAction").TEXT[0];
        addToBot(new SelectCardsInHandAction(magicNumber, upgradeText, card -> card.canUpgrade(), cards -> {
            for (AbstractCard c : cards) {
                c.upgrade();
                c.superFlash();
                c.applyPowers();
            }
        }));;
    }
}
