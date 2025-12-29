package cardexpansionpack.cards.colorless;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class Stockpile extends BaseCard {
    public static final String ID = makeID("Stockpile");

    private static final CardStats INFO = new CardStats(
        CardColor.COLORLESS,
        CardType.SKILL,
        CardRarity.UNCOMMON,
        CardTarget.SELF,
        1
    );

    private static final int BASE_CARDS_TO_DRAW = 2;
    private static final int BASE_CARDS_TO_DRAW_UPGRADE = 1;
    private static final int EXTRA_DRAW_PER_PLAY = 1;

    public Stockpile() {
        super(ID, INFO);
        setMagic(BASE_CARDS_TO_DRAW, BASE_CARDS_TO_DRAW_UPGRADE);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Stockpile();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(magicNumber));
        upgradeMagicNumber(EXTRA_DRAW_PER_PLAY);
    }
}
