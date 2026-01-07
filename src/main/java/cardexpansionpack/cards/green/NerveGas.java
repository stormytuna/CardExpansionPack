package cardexpansionpack.cards.green;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class NerveGas extends BaseCard {
    public static final String ID = makeID("NerveGas");

    private static final CardStats INFO = new CardStats(
        CardColor.GREEN,
        CardType.SKILL,
        CardRarity.UNCOMMON,
        CardTarget.ALL_ENEMY,
        1
    );

    public NerveGas() {
        super(ID, INFO);
        setExhaust(true);
    }

    @Override
    public AbstractCard makeCopy() {
        return new NerveGas();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (mo.isDeadOrEscaped()) {
                continue;
            }

            int numDebuffs = 0;
            for (AbstractPower po : mo.powers) {
                if (po.type == PowerType.DEBUFF) {
                    numDebuffs++;
                }
            }

            if (upgraded) {
                numDebuffs++;
            }

            if (numDebuffs <= 0) {
                continue;
            }

            addToBot(new ApplyPowerAction(mo, p, new StrengthPower(mo, numDebuffs * -1)));
        }
    }
}
