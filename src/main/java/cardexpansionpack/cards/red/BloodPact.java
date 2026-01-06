package cardexpansionpack.cards.red;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.powers.BloodPactMonsterPower;
import cardexpansionpack.powers.BloodPactPlayerPower;
import cardexpansionpack.util.CardStats;

public class BloodPact extends BaseCard {
    public static final String ID = makeID("BloodPact");

    private static final CardStats INFO = new CardStats(
        CardColor.RED,
        CardType.SKILL,
        CardRarity.UNCOMMON,
        CardTarget.ENEMY,
        1
    );

    public BloodPact() {
        super(ID, INFO);
        setCostUpgrade(0);
        setExhaust(true);
    }

    @Override
    public AbstractCard makeCopy() {
        return new BloodPact();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new BloodPactMonsterPower(m)));
        if (!p.hasPower(BloodPactPlayerPower.ID)) {
            addToBot(new ApplyPowerAction(p, p, new BloodPactPlayerPower(p)));
        }
    }
}

