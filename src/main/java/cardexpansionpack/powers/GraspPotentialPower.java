package cardexpansionpack.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import cardexpansionpack.CardExpansionPack;

public class GraspPotentialPower extends BasePower {
    public static final String ID = CardExpansionPack.makeID("GraspPotentialPower");

    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    public GraspPotentialPower(AbstractCreature owner, int amount) {
        super(ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            description = DESCRIPTIONS[0];
        } else {
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        addToTop(new RemoveSpecificPowerAction(owner, owner, this));
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.color == CardColor.COLORLESS && !card.purgeOnUse && amount > 0) {
            flash();
            amount--;
            updateDescription();
            if (amount <= 0) {
                addToTop(new RemoveSpecificPowerAction(owner, owner, this));
            }

            AbstractMonster target = null;
            if (action.target != null) {
                target  = (AbstractMonster)action.target;
            }

            AbstractCard copy = card.makeSameInstanceOf();
            AbstractDungeon.player.limbo.addToBottom(copy);
            copy.current_x = card.current_x;
            copy.current_y = card.current_y;
            copy.target_x = (Settings.WIDTH / 2f) - (300f * Settings.scale);
            copy.target_y = (Settings.HEIGHT / 2f);
            if (target != null) {
                copy.calculateCardDamage(target);
            }
            copy.purgeOnUse = true;
            AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(copy, target, copy.energyOnUse, true, true), true);
        }
    }
}
