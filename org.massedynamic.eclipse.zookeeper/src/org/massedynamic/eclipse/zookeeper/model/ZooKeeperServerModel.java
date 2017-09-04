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

package org.massedynamic.eclipse.zookeeper.model;

import org.massedynamic.eclipse.core.model.DataModel;
import org.massedynamic.eclipse.core.model.DataModelManager;
import org.massedynamic.eclipse.jmx.data.JmxConnection;
import org.massedynamic.eclipse.jmx.data.JmxConnectionDescriptor;
import org.massedynamic.eclipse.jmx.model.JmxConnectionModel;
import org.massedynamic.eclipse.jmx.model.JmxConnectionModelSource;
import org.massedynamic.eclipse.jmx.runtime.JmxConnectionDescriptorFiles;
import org.massedynamic.eclipse.zookeeper.ZooKeeperActivator;
import org.massedynamic.eclipse.zookeeper.data.ZooKeeperServer;
import org.massedynamic.eclipse.zookeeper.data.ZooKeeperServerDescriptor;

/**
 * {@link DataModel} wrapper for a {@link ZooKeeperServer}. The model key is a {@link ZooKeeperServerDescriptor}
 * and the model data is a {@link ZooKeeperServer}.
 * 
 * @author Mark Masse
 */
public final class ZooKeeperServerModel extends
        DataModel<ZooKeeperServerModel, ZooKeeperServerDescriptor, ZooKeeperServer> {

    private final DataModelManager<JmxConnectionModel, JmxConnectionDescriptor, JmxConnection> _JmxConnectionModelManager;
    private final ZooKeeperConnectionModel _ZooKeeperConnectionModel;

    /**
     * Constructor.
     * 
     * @param descriptor The {@link ZooKeeperServerDescriptor}.
     * @param zooKeeperConnectionModel The {@link ZooKeeperConnectionModel} that owns this server model.
     */
    ZooKeeperServerModel(ZooKeeperServerDescriptor descriptor, ZooKeeperConnectionModel zooKeeperConnectionModel) {
        super(descriptor);

        _ZooKeeperConnectionModel = zooKeeperConnectionModel;

        JmxConnectionDescriptorFiles jmxConnectionDescriptorFiles = ZooKeeperActivator.getDefault()
                .getZooKeeperConnectionDescriptorFiles().getJmxConnectionDescriptorFiles();

        JmxConnectionModelSource jmxConnectionModelSource = new JmxConnectionModelSource(jmxConnectionDescriptorFiles,
                this);

        _JmxConnectionModelManager = new DataModelManager<JmxConnectionModel, JmxConnectionDescriptor, JmxConnection>(
                jmxConnectionModelSource);
    }

    /**
     * Returns the {@link JmxConnectionModel} associated with this server model.
     * 
     * @return The {@link JmxConnectionModel} associated with this server model.
     */
    public JmxConnectionModel getJmxConnectionModel() {
        ZooKeeperServer zooKeeperServer = getData();
        JmxConnectionDescriptor jmxConnectionDescriptor = zooKeeperServer.getDescriptor().getJmxConnectionDescriptor();
        if (jmxConnectionDescriptor == null) {
            return null;
        }
        return _JmxConnectionModelManager.getModel(jmxConnectionDescriptor);
    }

    @Override
    public ZooKeeperConnectionModel getOwnerModel() {
        return _ZooKeeperConnectionModel;
    }

    @Override
    public ZooKeeperConnectionModel getParentModel() {
        // In this conceptual model the server is also a direct child of the JMX connection
        return getOwnerModel();
    }

    @Override
    protected ZooKeeperServerModel getThis() {
        return this;
    }

    @Override
    protected void hookAfterDestroyed() {
        _JmxConnectionModelManager.destroy();
    }

}
