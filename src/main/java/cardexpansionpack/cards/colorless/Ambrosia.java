package cardexpansionpack.cards.colorless;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.powers.AmbrosiaPower;
import cardexpansionpack.util.CardStats;

public class Ambrosia extends BaseCard {
    public static final String ID = makeID("Ambrosia");

    private static final CardStats INFO = new CardStats(
        CardColor.COLORLESS,
        CardType.SKILL,
        CardRarity.UNCOMMON,
        CardTarget.SELF,
        0
    );

    private static final int CARDS_TO_DRAW = 1;
    private static final int CARDS_TO_DRAW_UPGRADE = 1;

    public Ambrosia() {
        super(ID, INFO);
        setMagic(CARDS_TO_DRAW, CARDS_TO_DRAW_UPGRADE);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Ambrosia();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(magicNumber));
        if (!p.hasPower(AmbrosiaPower.ID)) {
            addToBot(new ApplyPowerAction(p, p, new AmbrosiaPower(p)));
        }
    }
}
