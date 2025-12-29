package cardexpansionpack.cards.colorless;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class Jackpot extends BaseCard {
    public static final String ID = makeID("Jackpot");

    private static final CardStats INFO = new CardStats(
        CardColor.COLORLESS,
        CardType.SKILL,
        CardRarity.RARE,
        CardTarget.SELF,
        -1
    );

    private static final int ENERGY_GAIN = 1;
    private static final int ENERGY_GAIN_UPGRADE = 1;

    public Jackpot() {
        super(ID, INFO);
        setMagic(ENERGY_GAIN, ENERGY_GAIN_UPGRADE);
        setExhaust(true);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Jackpot();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int cardsToDraw = energyOnUse;

        if (p.hasRelic(ChemicalX.ID)) {
            p.getRelic(ChemicalX.ID).flash();
            cardsToDraw += 2;
        }

        addToBot(new DrawCardAction(cardsToDraw));
        addToBot(new GainEnergyAction(magicNumber));

        if (!this.freeToPlayOnce) {
            p.energy.use(EnergyPanel.totalCount);
        }
    }
}
