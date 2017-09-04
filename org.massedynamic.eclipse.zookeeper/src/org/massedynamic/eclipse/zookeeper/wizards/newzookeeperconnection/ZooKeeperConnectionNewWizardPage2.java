/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.massedynamic.eclipse.zookeeper.wizards.newzookeeperconnection;

import org.eclipse.swt.widgets.Composite;

import java.util.List;

import org.massedynamic.eclipse.core.widgets.grid.GridComposite;
import org.massedynamic.eclipse.core.wizards.GridWizardPage;
import org.massedynamic.eclipse.zookeeper.data.AuthInfo;

/**
 * TODO: Comment.
 * 
 * @author Mark Masse
 */
public class ZooKeeperConnectionNewWizardPage2 extends GridWizardPage {

    /**
     * TODO: Comment.
     * 
     * @param wizard
     */
    public ZooKeeperConnectionNewWizardPage2(ZooKeeperConnectionNewWizard wizard) {
        super(wizard);
    }

    public List<AuthInfo> getAuthInfos() {
        ZooKeeperConnectionComposite2 composite = (ZooKeeperConnectionComposite2) getGridComposite();
        return composite.getAuthInfos();
    }

    @Override
    protected GridComposite createGridComposite(Composite parent) {
        return new ZooKeeperConnectionComposite2(parent);
    }

}