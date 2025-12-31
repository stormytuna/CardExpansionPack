package cardexpansionpack.cards.purple;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class Prediction extends BaseCard {
    public static final String ID = makeID("Prediction");

    private static final CardStats INFO = new CardStats(
        CardColor.PURPLE,
        CardType.SKILL,
        CardRarity.UNCOMMON,
        CardTarget.SELF,
        1
    );

    private static final int SCRY = 2;
    private static final int SCRY_UPGRADE = 1;

    private static final String DRAW_KEY = "draw";
    private static final int DRAW = 2;
    private static final int DRAW_UPGRADE = 1;

    public Prediction() {
        super(ID, INFO);
        setMagic(SCRY, SCRY_UPGRADE);
        setCustomVar(DRAW_KEY, DRAW, DRAW_UPGRADE);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Prediction();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ScryAction(magicNumber));
        addToBot(new DrawCardAction(customVar(DRAW_KEY)));
    }
}

