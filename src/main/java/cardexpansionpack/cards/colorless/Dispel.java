package cardexpansionpack.cards.colorless;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class Dispel extends BaseCard {
    public static final String ID = makeID("Dispel");

    private static final CardStats INFO = new CardStats(
        CardColor.COLORLESS,
        CardType.SKILL,
        CardRarity.RARE,
        CardTarget.SELF,
        1
    );

    public Dispel() {
        super(ID, INFO);
        setExhaust(true);
        setCostUpgrade(0);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Dispel();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractPower power : p.powers) {
            if (power.type == PowerType.DEBUFF) {
                addToBot(new RemoveSpecificPowerAction(p, p, power));
            }
        }
    }
}
