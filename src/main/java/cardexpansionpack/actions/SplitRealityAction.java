package cardexpansionpack.actions;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Safety;
import com.megacrit.cardcrawl.cards.tempCards.Smite;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

import basemod.BaseMod;

public class SplitRealityAction extends AbstractGameAction {
    private boolean haveChosenCard = false;

    public SplitRealityAction() {
        actionType = ActionType.CARD_MANIPULATION;
        duration = startDuration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (duration == startDuration) {
            ArrayList<AbstractCard> choices = new ArrayList<>();
            choices.add(new Smite());
            choices.add(new Safety());
            AbstractDungeon.cardRewardScreen.customCombatOpen(choices, CardRewardScreen.TEXT[1], true);

            tickDuration();

            return;
        }

        if (!haveChosenCard) {
            if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
                AbstractCard chosenCard = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();
                chosenCard.current_x = -1000f * Settings.xScale;
                if (AbstractDungeon.player.hand.size() < BaseMod.MAX_HAND_SIZE) {
                    AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(chosenCard, Settings.WIDTH / 2f, Settings.HEIGHT / 2f));
                } else {
                    AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(chosenCard, Settings.WIDTH / 2f, Settings.HEIGHT / 2f));
                }

                AbstractDungeon.cardRewardScreen.discoveryCard = null;
            }

            haveChosenCard = true;
        }

        tickDuration();
    }
}
