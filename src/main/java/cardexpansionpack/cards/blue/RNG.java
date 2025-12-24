package cardexpansionpack.cards.blue;

import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class RNG extends BaseCard {
    public static final String ID = makeID("RNG");

    private static final CardStats INFO = new CardStats(
        CardColor.BLUE,
        CardType.SKILL,
        CardRarity.RARE,
        CardTarget.SELF,
        -1
    );

    public RNG() {
        super(ID, INFO);
        setExhaust(true);
    }

    @Override
    public AbstractCard makeCopy() {
        return new RNG();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int cardsToPlay = energyOnUse;

        if (upgraded) {
            cardsToPlay++;
        }

        if (p.hasRelic(ChemicalX.ID)) {
            p.getRelic(ChemicalX.ID).flash();
            cardsToPlay += 2;
        }

        for (int i = 0; i < cardsToPlay; i++) {
            addToBot(new PlayTopCardAction(AbstractDungeon.getCurrRoom().monsters.getRandomMonster(null, true, AbstractDungeon.cardRandomRng), false));
        }

        if (!this.freeToPlayOnce) {
            p.energy.use(EnergyPanel.totalCount);
        }
    }
}

