package cardexpansionpack.cards.blue;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.powers.TrialAndErrorPower;
import cardexpansionpack.util.CardStats;

public class TrialAndError extends BaseCard {
    public static final String ID = makeID("TrialAndError");

    private static final CardStats INFO = new CardStats(
        CardColor.BLUE,
        CardType.POWER,
        CardRarity.UNCOMMON,
        CardTarget.SELF,
        1
    );

    public TrialAndError() {
        super(ID, INFO);
        setInnate(false, true);
    }

    @Override
    public AbstractCard makeCopy() {
        return new TrialAndError();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hasPower(TrialAndErrorPower.ID)) {
            return;
        }

        addToBot(new ApplyPowerAction(p, p, new TrialAndErrorPower(p)));
    }
}

