package cardexpansionpack.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.watcher.MantraPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class CondemnAction extends AbstractGameAction {
    private DamageInfo info;

    public CondemnAction(AbstractCreature target, DamageInfo info) {
        this.info = info;
        setValues(target, info);

        actionType = ActionType.DAMAGE;
        duration = startDuration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (shouldCancelAction())  {
            isDone = true;
            return;
        }

        tickDuration();

        if (isDone) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(target.hb.cX, target.hb.cY, AttackEffect.BLUNT_HEAVY, false));
            target.damage(info);
            if (target.lastDamageTaken > 0) {
                if (target.lastDamageTaken > 1) {
                    addToTop(new ApplyPowerAction(source, source, new MantraPower(source, target.lastDamageTaken - 1)));
                }

                // Mantra applies divinity only when stacked, so we apply twice
                //   in case player goes from 0 to 10
                addToTop(new ApplyPowerAction(source, source, new MantraPower(source, 1)));
            }

            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            } else {
                addToTop(new WaitAction(0.1f));
            }
        }
    }
}
