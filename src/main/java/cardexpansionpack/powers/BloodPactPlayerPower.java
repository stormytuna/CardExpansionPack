package cardexpansionpack.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import cardexpansionpack.CardExpansionPack;

public class BloodPactPlayerPower extends BasePower {
    public static final String ID = CardExpansionPack.makeID("BloodPactPlayerPower");

    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public BloodPactPlayerPower(AbstractCreature owner) {
        super(ID, TYPE, TURN_BASED, owner, -1);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    @Override
    public void wasHPLost(DamageInfo info, int damageAmount) {
        boolean hurtEnemy = false;
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (m.isDeadOrEscaped() || !m.hasPower(BloodPactMonsterPower.ID)) {
                continue;
            }

            hurtEnemy = true;
            DamageInfo damageInfo = new DamageInfo(owner, damageAmount, DamageType.THORNS);
            addToBot(new DamageAction(m, damageInfo, AttackEffect.FIRE));
        }

        if (hurtEnemy) {
            flash();
        }
    }
}
