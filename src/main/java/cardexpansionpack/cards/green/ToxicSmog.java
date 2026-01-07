package cardexpansionpack.cards.green;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class ToxicSmog extends BaseCard {
    public static final String ID = makeID("ToxicSmog");

    private static final CardStats INFO = new CardStats(
        CardColor.GREEN,
        CardType.SKILL,
        CardRarity.UNCOMMON,
        CardTarget.ALL_ENEMY,
        -1
    );

    private static final int POISON = 4;

    public ToxicSmog() {
        super(ID, INFO);
        setMagic(POISON);
    }

    @Override
    public AbstractCard makeCopy() {
        return new ToxicSmog();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int poisonTimes = energyOnUse;

        if (upgraded) {
            poisonTimes++;
        }

        if (p.hasRelic(ChemicalX.ID)) {
            p.getRelic(ChemicalX.ID).flash();
            poisonTimes += 2;
        }

        for (int i = 0; i < poisonTimes; i++) {
            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (mo.isDeadOrEscaped()) {
                    continue;
                }

                addToBot(new ApplyPowerAction(mo, p, new PoisonPower(mo, p, magicNumber)));
            }
        }

        if (!this.freeToPlayOnce) {
            p.energy.use(EnergyPanel.totalCount);
        }
    }
}
